import { DisciplineModule } from './discipline.module';

describe('DisciplineModule', () => {
  let disciplineModule: DisciplineModule;

  beforeEach(() => {
    disciplineModule = new DisciplineModule();
  });

  it('should create an instance', () => {
    expect(disciplineModule).toBeTruthy();
  });
});
